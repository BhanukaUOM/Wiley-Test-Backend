package com.sadheera.wileytest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController
{

  @RequestMapping(method=RequestMethod.GET)
  public ResponseEntity<?> index()
  {
    return ResponseEntity.ok("Wiley Online Library - Coding Test Backend");
  }

}