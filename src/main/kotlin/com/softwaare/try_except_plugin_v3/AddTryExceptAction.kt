package com.softwaare.try_except_plugin_v3

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.codeStyle.CodeStyleManager
import com.jetbrains.python.psi.PyFile

class AddTryExceptAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("AddTryExceptAction triggered")  // Debug log
        val project = e.project ?: run {
            println("Project not found")  // Debug log
            return
        }
        val editor = e.getData(CommonDataKeys.EDITOR) ?: run {
            println("Editor not found")  // Debug log
            return
        }
        val file = e.getData(CommonDataKeys.PSI_FILE)
        if (file is PyFile) {
            println("File is a Python file")
        } else {
            println("File is not a Python file")
        }

        val selectionModel = editor.selectionModel
        val selectedText = selectionModel.selectedText ?: run {
            println("No text selected")  // Debug log
            return
        }

        WriteCommandAction.runWriteCommandAction(project) {
            println("Inserting try-except block")  // Debug log
            val document = editor.document
            val startOffset = selectionModel.selectionStart
            val endOffset = selectionModel.selectionEnd

            // Add try-except block with correct indentation
            val indentedText = selectedText.lines().joinToString("\n") { line ->
                if (line.isNotBlank()) "    $line" else line  // Indent only non-blank lines
            }

            // Manually format the try-except block with proper indentation
            val tryExceptBlock = StringBuilder()
            tryExceptBlock.append("try:\n")
            tryExceptBlock.append(indentedText.prependIndent("    "))
            tryExceptBlock.append("\n    except Exception as e:\n")
            tryExceptBlock.append("        print(f\"An error occurred: {e}\")")

            val tryExceptBlockStr = tryExceptBlock.toString()

            println("Try-except block: $tryExceptBlockStr")  // Debug log
            document.replaceString(startOffset, endOffset, tryExceptBlockStr)

            // Reformat to ensure correct code style
            val psiFile = file ?: return@runWriteCommandAction
            CodeStyleManager.getInstance(project).reformatText(psiFile, startOffset, startOffset + tryExceptBlockStr.length)
            println("Try-except block inserted and reformatted")  // Debug log
        }
    }
}