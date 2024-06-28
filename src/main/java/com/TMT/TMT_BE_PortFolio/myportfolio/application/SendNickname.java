package com.TMT.TMT_BE_PortFolio.myportfolio.application;


import com.TMT.TMT_BE_PortFolio.myportfolio.dto.FeignClientResponseDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.FeignClientResponseMapperDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-server", url = "${FEIGNCLIENT.URL}")
public interface SendNickname {

    @GetMapping("/nickname-uuid")
    FeignClientResponseMapperDto sendNickname(@RequestParam("nickname") String nickname);

}
