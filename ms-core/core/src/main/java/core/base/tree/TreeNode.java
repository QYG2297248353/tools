package core.base.tree;

import java.util.List;

public interface TreeNode<T extends TreeNode<T>> {
    Object getId();

    Object getParentId();

    void setChildren(List<T> children);
}
