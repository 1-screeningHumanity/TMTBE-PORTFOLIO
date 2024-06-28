package com.TMT.TMT_BE_PortFolio.myportfolio.presentation;

import com.TMT.TMT_BE_PortFolio.global.common.response.BaseResponse;
import com.TMT.TMT_BE_PortFolio.global.common.token.DecodingToken;
import com.TMT.TMT_BE_PortFolio.myportfolio.application.MyPortFolioServiceImp;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.HasStockResponseVo;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.UserStockResponseVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPortfolioController {


    private final DecodingToken decodingToken;
    private final MyPortFolioServiceImp myPortFolioServiceImp;

    //produces = MediaType.TEXT_EVENT_STREAM_VALUE
    @GetMapping( "/myportfolio")
    public BaseResponse<List<HasStockResponseVo>> myPortFolio(@RequestHeader
            ("Authorization") String jwt){

        String uuid = decodingToken.getUuid(jwt);
        List<HasStockResponseVo> list = myPortFolioServiceImp.getMyPortFolio(uuid);

        return new BaseResponse<>(list);
    }


    @GetMapping("/members")
    public BaseResponse<List<UserStockResponseVo>> memberPortFolio(@RequestParam
            ("nickname") String nickname){

        List<UserStockResponseVo> userStockResponseVo = myPortFolioServiceImp.sendNickname(nickname);
        return new BaseResponse<>(userStockResponseVo);


    }

}
