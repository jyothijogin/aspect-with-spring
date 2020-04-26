package com.example.aspect.service;

import com.example.aspect.aop.TrackEntry;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    @TrackEntry
    public void doSomething(){

    }
}
