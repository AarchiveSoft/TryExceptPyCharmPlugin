package com.softwaare.try_except_plugin_v3

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.PyTryExceptStatement
import com.jetbrains.python.psi.PyElementGenerator
import com.jetbrains.python.psi.PyElement
import com.jetbrains.python.psi.LanguageLevel

class RemoveTryExceptAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val file = e.getData(CommonDataKeys.PSI_FILE) ?: return

        val caretOffset = editor.caretModel.offset
        val elementAtCaret = file.findElementAt(caretOffset)
        val tryExceptStatement = PsiTreeUtil.getParentOfType(elementAtCaret, PyTryExceptStatement::class.java) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val document = editor.document
            val tryStartOffset = tryExceptStatement.textRange.startOffset
            val exceptStatement = tryExceptStatement.exceptParts.firstOrNull() ?: return
            val exceptEndOffset = exceptStatement.textRange.endOffset

            // Get the code between the try and except
            val codeBlock = document.getText(tryExceptStatement.tryPart.textRange)
            val undentedCode = codeBlock.replaceIndent("")

            // Replace try-except block with dedented code
            document.replaceString(tryStartOffset, exceptEndOffset, undentedCode)
        }
    }
}
