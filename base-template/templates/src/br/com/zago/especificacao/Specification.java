package br.com.zago.especificacao;

import java.util.List;
import java.util.function.Predicate;

public interface Specification<T> {
	boolean isSatisfiedBy(T t);

	default Specification<T> and(Specification<T> other) {
		return new AndSpecification<>(this, other);
	}

	default Specification<T> or(Specification<T> other) {
		return new OrSpecification<>(this, other);
	}

	default Specification<T> not() {
		return new NotSpecification<>(this);
	}

	default Predicate<T> toPredicate() {
		return this::isSatisfiedBy;
	}

	default List<T> filter(List<T> items) {
		return items.stream().filter(this::isSatisfiedBy).toList();
	}
}

class AndSpecification<T> implements Specification<T> {
	
	private final Specification<T> left;
	private final Specification<T> right;

	AndSpecification(Specification<T> left, Specification<T> right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean isSatisfiedBy(T t) {
		return left.isSatisfiedBy(t) && right.isSatisfiedBy(t);
	}
}

class OrSpecification<T> implements Specification<T> {
    private final Specification<T> left;
    private final Specification<T> right;

    OrSpecification(Specification<T> left, Specification<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return left.isSatisfiedBy(t) || right.isSatisfiedBy(t);
    }
}

class NotSpecification<T> implements Specification<T> {
    private final Specification<T> specification;

    NotSpecification(Specification<T> specification) {
        this.specification = specification;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return !specification.isSatisfiedBy(t);
    }
}