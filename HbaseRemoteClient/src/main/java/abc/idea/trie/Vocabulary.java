package abc.idea.trie;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/23/15
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Vocabulary {
    boolean add(String word);
    boolean isPrefix(String prefix);
    boolean contains(String word);
}
