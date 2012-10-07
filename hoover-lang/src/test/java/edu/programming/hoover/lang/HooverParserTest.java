package edu.programming.hoover.lang;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Java Doc here
 *
 * @author Victor Polischuk
 * @since 05/10/12 14:02
 */
public class HooverParserTest extends Assert {

    @Test
    public void testParserSingleBinaryNumber() throws RecognitionException {
        Tree tree = getTree(getStringStream("11b"));

        assertEquals(0, tree.getChildCount());
    }

    @Test(expected = MismatchedTokenException.class)
    public void testParserHeaderWithoutLiteral() throws RecognitionException {
        getTree(getStringStream("program asd"));
    }

    @Test
    public void testParserHeaderCorrect() throws RecognitionException {
        Tree tree = getTree(getStringStream("program  'testing'"));

        assertEquals(2, tree.getChildCount());
    }

    @Test
    public void testParserBodyCorrect() throws RecognitionException {
        Tree tree = getTree(getStringStream("program  'testing' while can left loop move left end loop if match then take end if"));

        assertEquals(4, tree.getChildCount());
    }

    @Test
    public void testParserBodyComplexCondition() throws RecognitionException, IOException {
        Tree tree = getTree(getInputStream("edu/programming/hoover/lang/HooverCondition.txt"));

        assertEquals(3, tree.getChildCount());
    }

    @Test
    public void testParserBodyArithmeticCondition() throws RecognitionException, IOException {
        Tree tree = getTree(getInputStream("edu/programming/hoover/lang/HooverArithmetic.txt"));

        assertEquals(3, tree.getChildCount());
    }

    private Tree getTree(CharStream stream) throws RecognitionException {
        HooverLexer hooverLexer = new HooverLexer(stream);
        TokenStream hooverTokenStream = new CommonTokenStream(hooverLexer);
        HooverParser hooverParser = new HooverParser(hooverTokenStream);

        Tree tree = (Tree) hooverParser.parseApplication().getTree();

        assertTree(tree);

        return tree;
    }

    private static void assertTree(Tree tree) throws RecognitionException {
        if (tree == null) {
            return;
        }

        if (tree instanceof CommonErrorNode) {
            throw ((CommonErrorNode) tree).trappedException;
        }

        for (int i = 0; i < tree.getChildCount(); i++) {
            assertTree(tree.getChild(i));
        }
    }

    private static ANTLRInputStream getInputStream(String inputStream) throws IOException {
        return new ANTLRInputStream(HooverParserTest.class.getClassLoader().getResourceAsStream(inputStream));
    }

    private static ANTLRStringStream getStringStream(String code) {
        return new ANTLRStringStream(code);
    }
}
