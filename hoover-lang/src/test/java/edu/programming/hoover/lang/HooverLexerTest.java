package edu.programming.hoover.lang;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;
import org.junit.Assert;
import org.junit.Test;

/**
 * Java Doc here
 *
 * @author Victor Polischuk
 * @since 05/10/12 14:02
 */
public class HooverLexerTest extends Assert {
    private final HooverLexer hooverLexer = new HooverLexer();

    @Test
    public void testLexerSingleBinaryNumber() {
        hooverLexer.setCharStream(new ANTLRStringStream("11b"));

        Token token = hooverLexer.nextToken();

        assertEquals(HooverLexer.INTEGER, token.getType());
    }

    @Test
    public void testLexerSingleOctalNumber() {
        hooverLexer.setCharStream(new ANTLRStringStream("11c"));

        Token token = hooverLexer.nextToken();

        assertEquals(HooverLexer.INTEGER, token.getType());
    }

    @Test
    public void testLexerSingleHexNumber() {
        hooverLexer.setCharStream(new ANTLRStringStream("11h"));

        Token token = hooverLexer.nextToken();

        assertEquals(HooverLexer.INTEGER, token.getType());
    }

    @Test
    public void testLexerSingleDecimalNumber() {
        hooverLexer.setCharStream(new ANTLRStringStream("11"));

        Token token = hooverLexer.nextToken();

        assertEquals(HooverLexer.INTEGER, token.getType());
    }
}
