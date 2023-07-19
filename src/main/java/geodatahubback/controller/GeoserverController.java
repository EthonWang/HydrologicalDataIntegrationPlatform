package geodatahubback.controller;

import geodatahubback.config.concurrent.RateLimit;
import geodatahubback.entity.JsonResult;
import geodatahubback.service.GeoserverService;
import geodatahubback.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/7/17
 **/
@CrossOrigin
@RestController
public class GeoserverController {

    @Autowired
    GeoserverService geoserverService;



    @ApiOperation(value = "upTif2Geoserver")
    @GetMapping(value = "/upTif2Geoserver")
    public JsonResult upTif2Geoserver(@RequestParam("realName") String realName,
                                      @RequestParam("storeName") String storeName){
        return geoserverService.upTif2Geoserver(realName,storeName);
    }

    @ApiOperation(value = "upShp2Geoserver")
    @GetMapping(value = "/upShp2Geoserver")
    public JsonResult upShp2Geoserver(@RequestParam("realName") String realName,
                                      @RequestParam("storeName") String storeName){
        return geoserverService.upShp2Geoserver(realName,storeName);
    }
}
