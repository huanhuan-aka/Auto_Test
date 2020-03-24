package com.auto.core;


import com.auto.common.ResponseResult;
import com.auto.domain.Project;
import com.auto.domain.User;
import com.auto.service.ProjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * get接口在postman中调试时,参数要放在param中
     */

    @GetMapping("/showAllProjects")
    @ApiOperation(value = "展示当前用户全部项目",httpMethod = "Get")
    public ResponseResult showAllProjects(Integer userId) {
        //根据userId查询到该用户创建的项目列表
        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("create_user",userId);
        List<Project> projectList = projectService.list(queryCondition);

        return new ResponseResult("0", projectList,"项目列表已查询!");
    }


    @PostMapping("/addProject")
    @ApiOperation(value = "新增项目",httpMethod = "POST")
    public ResponseResult addProject(Project project) {
        User createUser = (User) SecurityUtils.getSubject().getPrincipal();
        project.setCreateUser(createUser.getId());
        projectService.save(project);

        return new ResponseResult("0","新增项目成功!");
    }


    @GetMapping("/{projectId}")
    @ApiOperation(value = "展示当前用户全部项目",httpMethod = "Get")
    public ResponseResult getProjectById(@PathVariable  Integer projectId) {
        //根据projectId查询项目
        Project project = projectService.getById(projectId);

        return new ResponseResult("0", project,"根据id查询项目信息");
    }

    @PutMapping("/{projectId}")
    @ApiOperation(value = "展示当前用户全部项目",httpMethod = "Put")
    public ResponseResult updateProjectSetById(@PathVariable("projectId") Integer projectId,Project project) {
        project.setId(projectId);
        User createUser = (User) SecurityUtils.getSubject().getPrincipal();
        project.setCreateUser(createUser.getId());
        projectService.updateById(project);

        return new ResponseResult("0","更新项目设置成功");
    }

    //TODO:前端联调--删除项目-->done
    @DeleteMapping("/{projectId}")
    @ApiOperation(value = "展示当前用户全部项目",httpMethod = "Delete")
    public ResponseResult delProjectSetById(@PathVariable("projectId") Integer projectId) {

        projectService.removeById(projectId);
        return new ResponseResult("0","删除项目设置成功");
    }


}
