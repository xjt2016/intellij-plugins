package com.intellij.tapestry.psi;

import com.intellij.lang.*;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.lang.xml.XMLParserDefinition;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexey Chmutov
 */
public class TmlParserDefinition implements ParserDefinition {
  @Override
  @NotNull
  public Lexer createLexer(Project project) {
    return new TmlLexer();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return TmlElementType.TML_FILE;
  }

  @Override
  @NotNull
  public TokenSet getWhitespaceTokens() {
    return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(XMLLanguage.class)).getWhitespaceTokens();
  }

  @Override
  @NotNull
  public TokenSet getCommentTokens() {
    return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(XMLLanguage.class)).getCommentTokens();
  }

  @Override
  @NotNull
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @Override
  @NotNull
  public PsiParser createParser(final Project project) {
    return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(XMLLanguage.class)).createParser(project);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new TmlFile(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
    final Lexer lexer = createLexer(left.getPsi().getProject());
    return XMLParserDefinition.canStickTokensTogetherByLexerInXml(left, right, lexer, 0);
  }

  @Override
  @NotNull
  public PsiElement createElement(ASTNode node) {
    throw new IllegalArgumentException("Unknown element: "+node);
  }
}

