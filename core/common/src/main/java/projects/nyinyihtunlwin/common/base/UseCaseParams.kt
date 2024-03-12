package projects.nyinyihtunlwin.common.base

/**
 * Interface representing params to be passed in for each interactor.
 * Implement this for each interactor that requires specific params.
 */
public interface UseCaseParams

/**
 * A special [InteractorParams] representing empty params.
 * Use this when the interactor requires no params.
 */
public object EmptyParams : UseCaseParams

data class PaginationParams(val currentPage: Int) : UseCaseParams

fun Int.toPaginationParams() = PaginationParams(this)
