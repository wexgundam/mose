package mose.core.restful;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
@Component
public class RestfulTemplate extends RestTemplate {
    // PUT
    public <T> ResponseEntity<T> put(String url, Object request, Class<T> responseType, Object... urlVariables) throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, HttpMethod.PUT, requestCallback, responseExtractor, urlVariables);
    }

    public <T> ResponseEntity<T> put(String url, Object request, Class<T> responseType, Map<String, ?> urlVariables) throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, HttpMethod.PUT, requestCallback, responseExtractor, urlVariables);
    }

    public <T> ResponseEntity<T> put(URI url, Object request, Class<T> responseType) throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, HttpMethod.PUT, requestCallback, responseExtractor);
    }


    // DELETE
    public <T> ResponseEntity<T> delete(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, HttpMethod.DELETE, null, responseExtractor, urlVariables);
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> responseType, Map<String, ?> urlVariables) throws RestClientException {
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, HttpMethod.DELETE, null, responseExtractor, urlVariables);
    }

    public <T> ResponseEntity<T> delete(URI url, Class<T> responseType) throws RestClientException {
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, HttpMethod.DELETE, null, responseExtractor);
    }
}
