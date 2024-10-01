package com.softwaare.try_except_plugin_v3

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiElement
import com.intellij.psi.codeStyle.CodeStyleManager
import java.lang.reflect.Method

class RemoveTryExceptAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("RemoveTryExceptAction triggered")
        val project = e.project ?: run {
            println("Project not found")
            return
        }
        val editor = e.getData(CommonDataKeys.EDITOR) ?: run {
            println("Editor not found")
            return
        }

        val file = e.getData(CommonDataKeys.PSI_FILE)
        println("File Type: ${file?.javaClass?.name}")

        if (file !is com.jetbrains.python.psi.PyFile) {
            println("File is not identified as a Python file using direct type check.")
        } else {
            println("Python file detected using direct type check.")
        }

        val fileClassName = file?.javaClass?.canonicalName
        if (fileClassName != null && fileClassName.contains("PyFileImpl")) {
            println("Python file detected using class name check.")
        } else {
            println("File is not identified as a Python file using class name check.")
        }

        val caretOffset = editor.caretModel.offset
        val elementAtCaret = file?.findElementAt(caretOffset)
        println("Caret Offset: $caretOffset")
        println("Element at caret: ${elementAtCaret?.javaClass?.name}, text: '${elementAtCaret?.text}'")

        elementAtCaret?.let {
            generateContextLog(it)
        }

        // Function that checks if an element is a PyTryExceptStatement or PyTryExceptStatementImpl
        var tryExceptStatement: PsiElement? = null

        var currentElement = elementAtCaret
        while (currentElement != null) {
            println("Parent Element: ${currentElement.javaClass.name}, text: '${currentElement.text}'")
            if (currentElement is com.jetbrains.python.psi.PyTryExceptStatement ||
                currentElement.javaClass.canonicalName == "com.jetbrains.python.psi.impl.PyTryExceptStatementImpl"
            ) {
                tryExceptStatement = currentElement
                break
            }
            currentElement = currentElement.parent
        }

        if (tryExceptStatement == null) {
            println("No PyTryExceptStatement found near caret")
            return
        }

        println("Try-except statement found: ${tryExceptStatement.text}")

        // Accessing the tryPart property via reflection
        val tryPartProperty: PsiElement? = try {
            val getTryPartMethod: Method = tryExceptStatement.javaClass.getMethod("getTryPart")
            getTryPartMethod.invoke(tryExceptStatement) as PsiElement
        } catch (ex: Exception) {
            println("Error accessing tryPart property: ${ex.message}")
            return
        }

        WriteCommandAction.runWriteCommandAction(project) {
            println("Removing try-except block")
            val document = editor.document

            // Get try part text range without 'try:' keyword
            val tryPartRange = tryPartProperty!!.textRange
            val tryKeywordEndOffset = tryPartRange.startOffset + 3 // Length of 'try'

            val codeBlock = document.getText(tryPartRange)

            // Remove first line that contains 'try:'
            val indentedLines = codeBlock.lines().drop(1)

            // Un-indent the rest of the lines
            val undentedCode = indentedLines.joinToString("\n") { line ->
                if (line.startsWith("    ")) line.substring(4) else line
            }

            document.replaceString(tryExceptStatement.textRange.startOffset, tryExceptStatement.textRange.endOffset, undentedCode)

            file?.let {
                CodeStyleManager.getInstance(project).reformatText(it, tryExceptStatement.textRange.startOffset, tryExceptStatement.textRange.startOffset + undentedCode.length)
            }
            println("Try-except block removed and reformatted")
        }
    }

    private fun generateContextLog(element: PsiElement) {
        println("Generating context log for element: ${element.text}")
        var currentElement: PsiElement? = element
        while (currentElement != null) {
            println("Element: ${currentElement.javaClass.name}, text: '${currentElement.text}'")
            currentElement = currentElement.parent
        }
    }
}