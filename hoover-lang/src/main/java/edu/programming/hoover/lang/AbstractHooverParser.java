package edu.programming.hoover.lang;

import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;

/**
 * The super class of the generated parser. It is extended by the generated
 * code because of the superClass optoin in the .g file.
 *
 * This class contains any helper functions used within the parser
 * grammar itself, as well as any overrides of the standard ANTLR Java
 * runtime methods, such as an implementation of a custom error reporting
 * method, symbol table populating methods and so on.
 *
 * @author Victor Polischuk
 * @since 05/10/12 12:12
 */
public abstract class AbstractHooverParser extends Parser

{
    /**
     * Create a new parser instance, pre-supplying the input token stream.
     * 
     * @param input The stream of tokens that will be pulled from the lexer
     */
    protected AbstractHooverParser(TokenStream input) {
        super(input);
    }

    /**
     * Create a new parser instance, pre-supplying the input token stream
     * and the shared state.
     *
     * This is only used when a grammar is imported into another grammar, but
     * we must supply this constructor to satisfy the super class contract.
     *
     * @param input The stream of tokens that will be pulled from the lexer
     * @param state The shared state object created by an interconnected grammar
     */
    protected AbstractHooverParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }


    /**
     * Creates the error/warning message that we need to show users/IDEs when
     * ANTLR has found a parsing error, has recovered from it and is now
     * telling us that a parsing exception occurred.
     *
     * @param tokenNames token names as known by ANTLR (which we ignore)
     * @param e The exception that was thrown
     */
    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {

        // This is just a placeholder that shows how to override this method
        //
        super.displayRecognitionError(tokenNames, e);
    }
}

