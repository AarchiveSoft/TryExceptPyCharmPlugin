package com.example.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.PyTryExceptStatement
import com.jetbrains.python.psi.PyElementGenerator

class RemoveTryExceptAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return
        val file = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE) ?: return

        val caretOffset = editor.caretModel.offset
        val elementAtCaret = file.findElementAt(caretOffset)
        val tryExceptStatement = PsiTreeUtil.getParentOfType(elementAtCaret, PyTryExceptStatement::class.java) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val body = tryExceptStatement.tryPart.statementList.statements
            val elementFactory = PyElementGenerator.getInstance(project)

            // Remove try and except parts
            tryExceptStatement.deleteChildRange(tryExceptStatement.tryPart.firstChild, tryExceptStatement.lastChild)

            // Add body back to the main block, dedented
            for (statement in body) {
                val newStatement = elementFactory.createFromText(LanguageLevel.PYTHON36, PyElement::class.java, statement.text)
                tryExceptStatement.parent.addBefore(newStatement, tryExceptStatement)
            }

            // Remove original tryExcept block
            tryExceptStatement.delete()
        }
    }
}
