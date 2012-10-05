package edu.programming.hoover.lang;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MismatchedTreeNodeException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.junit.Assert;
import org.junit.Test;

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
        hooverLexer.setCharStream(new ANTLRStringStream("program  'testing';"));

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
}
