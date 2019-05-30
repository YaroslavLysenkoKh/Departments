package comm.util.converter;

public interface DtoConverter<T, K> {

    K convert(T model);
}
