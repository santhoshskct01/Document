package com.bank.document.client;
import com.bank.document.modal.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient(value = "Document-api", url = "https://jsonplaceholder.typicode.com")
public interface ApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Post> readPost();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    Post readPostById(@PathVariable("id") String id);

}