package com.yhyt.health.controller;

import com.yhyt.health.service.api.ItemApi;
import com.yhyt.health.spring.AppResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 工作站服务包
 *
 * @author gsh
 * @create 2018-01-02 11:19
 **/
@RestController
@RequestMapping("/item")
public class ItemAppController {

    @Autowired
    private ItemApi itemApi;

    @ApiOperation(value = "获取服务包申请列表", notes = "获取服务包申请列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "departmentId", value = "科室id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "doctorState", value = "操作状态 1 待处理 2处理中 3已完成 4 已拒绝", paramType = "query", required = true, dataType = "String")
    })
    @GetMapping("/items")
    public AppResult getItems(
            @RequestParam(name = "departmentId") Long departmentId,
            @RequestParam(name = "doctorState") String doctorState
    ) {
        return  itemApi.getItems(departmentId,doctorState);
    }

    @ApiOperation(value = "获取服务包申请详情", notes = "获取服务包申请详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "path", required = true, dataType = "Long")
    })
    @GetMapping("/item/{id}")
    public AppResult getItem(
            @PathVariable Long id
    ) {
        return  itemApi.getItem(id);
    }

    @ApiOperation(value = "接受/结束 服务包申请服务", notes = "接受/结束 服务包申请服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "doctorState", value = "操作状态", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "任务id", paramType = "path", required = true, dataType = "String")
    })
    @PatchMapping("/{id}")
    public AppResult updateItemState(
            @RequestParam(name = "doctorState") String doctorState,
            @RequestParam(name = "userId",required = false) Long userId,
            @PathVariable Long id
    ) {
        return  itemApi.updateItemState(id,doctorState,userId);
    }

    @ApiOperation(value = "获取订单服务状态", notes = "获取订单服务状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", paramType = "path", required = true, dataType = "Long")
    })
    @GetMapping("/order/{orderId}")
    public AppResult getTaskServiceState(@PathVariable Long orderId) {
        return itemApi.getTaskServiceState(orderId);
    }

    @ApiOperation(value = "获取服务申请数量", notes = "获取服务申请数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "departmentId", value = "科室id", paramType = "query", required = true, dataType = "Long"),
    })
    @GetMapping("/getItemCounts")
    public AppResult getItemCounts(Long departmentId,@RequestParam("doctorStates") List<String> doctorStates) {
        return itemApi.getItemCounts(departmentId, doctorStates);
    }

    /**
     * 生成任务
     * @param itemId
     * @param orderId
     * @return
     */
    @ApiIgnore
    @PostMapping("/task/{itemId}")
    public AppResult genTaskService(
            @PathVariable Long itemId,
            @RequestParam Long orderId
    ) {
        return  itemApi.genTaskService(itemId,orderId);
    }
}
