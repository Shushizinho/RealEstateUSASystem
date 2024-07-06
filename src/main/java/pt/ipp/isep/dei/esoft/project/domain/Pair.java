package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The type Pair.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 */
public class Pair<T,S>{
    private T left;
    private S right;

    /**
     * Instantiates a new Pair.
     *
     * @param left  the left
     * @param right the right
     */
    public Pair(T left, S right){
        this.left = left;
        this.right = right;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    public T getLeft() {
        return left;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    public S getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + "-" + right;
    }

    @Override
    public Pair<T, S> clone() {
        return new Pair(this.left, this.right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
