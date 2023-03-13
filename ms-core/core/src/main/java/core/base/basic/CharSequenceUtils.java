package core.base.basic;

public class CharSequenceUtils {
    /**
     * 查找CharSequence中的最后一个索引，处理null
     * <p>
     * 空的CharSequence将返回-1。负起始位置返回-1。除非开始位置为负，否则空（“”）搜索CharSequence始终匹配。大于字符串长度的开始位置将搜索整个字符串。搜索从startPos开始并向后进行；忽略在开始位置之后开始的匹配。
     * </p>
     * demo:
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf(*, null, *)          = -1
     * StringUtils.lastIndexOf("aabaabaa", "a", 8)  = 7
     * StringUtils.lastIndexOf("aabaabaa", "b", 8)  = 5
     * StringUtils.lastIndexOf("aabaabaa", "ab", 8) = 4
     * StringUtils.lastIndexOf("aabaabaa", "b", 9)  = 5
     * StringUtils.lastIndexOf("aabaabaa", "b", -1) = -1
     * StringUtils.lastIndexOf("aabaabaa", "a", 0)  = 0
     * StringUtils.lastIndexOf("aabaabaa", "b", 0)  = -1
     * StringUtils.lastIndexOf("aabaabaa", "b", 1)  = -1
     * StringUtils.lastIndexOf("aabaabaa", "b", 2)  = 2
     * StringUtils.lastIndexOf("aabaabaa", "ba", 2)  = 2
     *
     * @param seq       字符串
     * @param searchSeq 要查找的字符串
     * @param startPos  从第几个开始查找
     * @return 返回查找到的位置
     */
    public static int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        if (seq == null || searchSeq == null) {
            return -1;
        }
        return seq.toString().lastIndexOf(searchSeq.toString(), startPos);
    }

    /**
     * 查找CharSequence中的第一个索引，处理null
     * <p>
     * 空的CharSequence将返回-1。负起始位置被视为零。空（“”）搜索CharSequence始终匹配。大于字符串长度的开始位置仅匹配空搜索CharSequence。
     * </P>
     * demo:
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf(*, null, *)          = -1
     * StringUtils.indexOf("", "", 0)           = 0
     * StringUtils.indexOf("", *, 0)            = -1 (except when * = "")
     * StringUtils.indexOf("aabaabaa", "a", 0)  = 0
     * StringUtils.indexOf("aabaabaa", "b", 0)  = 2
     * StringUtils.indexOf("aabaabaa", "ab", 0) = 1
     * StringUtils.indexOf("aabaabaa", "b", 3)  = 5
     * StringUtils.indexOf("aabaabaa", "b", 9)  = -1
     * StringUtils.indexOf("aabaabaa", "b", -1) = 2
     * StringUtils.indexOf("aabaabaa", "", 2)   = 2
     * StringUtils.indexOf("abc", "", 9)        = 3
     *
     * @param seq       字符串
     * @param searchSeq 要查找的字符串
     * @param startPos  从第几个开始查找
     * @return 返回查找到的位置
     */
    public static int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        if (seq == null || searchSeq == null) {
            return -1;
        }
        return seq.toString().indexOf(searchSeq.toString(), startPos);
    }
}
