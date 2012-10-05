package edu.programming.hoover.lang;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognizerSharedState;

/**

 * This is the super class for the lexer. It is extended by the lexer class generated from HooverLexer.g.
 *
 * Do not place code and declarations in the lexer .g files, use
 * a superclass like this and place all the support methods and
 * error overrides etc in the super class. This way you will keep
 * the lexer grammar clean and hunky dory.
 *
 * @author Victor Polischuk
 * @since 05/10/12 12:12
 */
public abstract class AbstractHooverLexer extends Lexer {
    /**
     * Default constructor for the lexer, when you do not yet know what
     * the character stream to be provided is.
     */
    public AbstractHooverLexer() {
    }

    /**
     * Create a new instance of the lexer using the given character stream as
     * the input to lex into tokens.
     *
     * @param input A valid character stream that contains the ruleSrc code you
     *              wish to compile (or lex at least)
     */
    public AbstractHooverLexer(CharStream input) {
        super(input);
    }

    /**
     * Internal constructor for ANTLR - do not use.
     *
     * @param input The character stream we are going to lex
     * @param state The shared state object, shared between all lexer components
     */
    public AbstractHooverLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }
}
