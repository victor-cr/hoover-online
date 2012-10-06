package edu.programming.hoover.lang;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Java Doc here
 *
 * @author Victor Polischuk
 * @since 05/10/12 14:02
 */
public class HooverParserTest extends Assert {
    private final HooverLexer hooverLexer = new HooverLexer();
    private final TokenStream hooverTokenStream = new CommonTokenStream(hooverLexer);
    private final HooverParser hooverParser = new HooverParser(hooverTokenStream);

    @Test(expected = MismatchedTokenException.class)
    public void testParserSingleBinaryNumber() throws RecognitionException {
        hooverLexer.setCharStream(new ANTLRStringStream("11b"));

        getTree();
    }

    @Test(expected = MismatchedTokenException.class)
    public void testParserHeaderWithoutLiteral() throws RecognitionException {
        hooverLexer.setCharStream(new ANTLRStringStream("program asd"));

        getTree();
    }

    @Test
    public void testParserHeaderCorrect() throws RecognitionException {
        hooverLexer.setCharStream(new ANTLRStringStream("program  'testing'"));

        Tree tree = getTree();

        assertEquals(2, tree.getChildCount());
    }

    @Test
    public void testParserBodyCorrect() throws RecognitionException {
        hooverLexer.setCharStream(new ANTLRStringStream("program  'testing' while can left loop move left end loop if match then take end if"));

        Tree tree = getTree();

        assertEquals(6, tree.getChildCount());
    }

    @Test
    public void testParserBodyComplexCondition() throws RecognitionException, IOException {
        hooverLexer.setCharStream(getInputStream("edu/programming/hoover/lang/HooverCondition.txt"));

        Tree tree = getTree();

        assertEquals(4, tree.getChildCount());
    }

    @Test
    public void testParserBodyArithmeticCondition() throws RecognitionException, IOException {
        hooverLexer.setCharStream(getInputStream("edu/programming/hoover/lang/HooverArithmetic.txt"));

        Tree tree = getTree();

        assertEquals(4, tree.getChildCount());
    }

    private Tree getTree() throws RecognitionException {
        Tree tree = (Tree) hooverParser.parseApplication().getTree();

        assertTree(tree);

        return tree;
    }

    private static void assertTree(Tree tree) throws RecognitionException {
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

}
