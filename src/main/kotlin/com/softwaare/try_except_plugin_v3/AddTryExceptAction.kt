package com.softwaare.try_except_plugin_v3

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.jetbrains.python.psi.PyElementGenerator
import com.jetbrains.python.psi.PyFile
import com.jetbrains.python.psi.PyElement
import com.jetbrains.python.psi.LanguageLevel

class AddTryExceptAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val file = e.getData(CommonDataKeys.PSI_FILE) as? PyFile ?: return

        val selectionModel = editor.selectionModel
        val selectedText = selectionModel.selectedText ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val elementFactory = PyElementGenerator.getInstance(project)
            val tryExceptText = """
                try:
                    $selectedText
                except Exception as e:
                    print(f"An error occurred: {e}")
            """.trimIndent()

            val newTryExceptBlock = elementFactory.createFromText(LanguageLevel.PYTHON36, PyElement::class.java, tryExceptText)
            file.add(newTryExceptBlock)
        }
    }
}
