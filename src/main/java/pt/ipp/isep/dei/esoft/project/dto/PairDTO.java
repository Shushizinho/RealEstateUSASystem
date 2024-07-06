package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Pair;

/**
 * The type Client dto.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 */
public class PairDTO<T, S> {

    private final T left;
    private final S right;


    /**
     * Instantiates a new Pair dto.
     *
     * @param left  the left
     * @param right the right
     */
    public PairDTO(T left, S right) {
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
    public PairDTO<T, S> clone() {
        return new PairDTO<>(this.left, this.right);
    }

    /**
     * To string dto string.
     *
     * @return the string
     */
    public String toStringDTO() {
        return left + "-" + right;
    }
}
