package com.TMT.TMT_BE_PortFolio.myportfolio.presentation;


import com.TMT.TMT_BE_PortFolio.global.common.response.BaseResponse;
import com.TMT.TMT_BE_PortFolio.global.common.token.DecodingToken;
import com.TMT.TMT_BE_PortFolio.myportfolio.application.MyPortFolioServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class MyPortfolioController {


    private final DecodingToken decodingToken;
    private final MyPortFolioServiceImp myPortFolioServiceImp;

    @GetMapping("/portfolio")
    public BaseResponse<Void> myPortFolio(@RequestHeader("Authorization") String jwt){

        String uuid = decodingToken.getUuid(jwt);
        myPortFolioServiceImp.getMyPortFolio(uuid);

        return  new BaseResponse<>();
    }

}
