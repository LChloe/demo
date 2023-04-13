package com.example.demo.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.entity.DemoTest;
import com.example.demo.utils.BaseClazzUtil;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/javers")
public class JaversController {
    public static final Logger LOGGER = LoggerFactory.getLogger(JaversController.class);

    @Autowired
    private Javers javers;

    //实体类的包全限定名前缀
    private final String packageName = "com.example.demo.entity.";

    /**
     * 查询快照
     * @param localId 配置id
     * @param entityName Class名
     * @param startTime 查询的起始时间
     * @param endTime 查询的结束时间
     * @return
     */
    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public Object findSnapshots(@RequestParam(required = false) Integer localId, String entityName, String startTime, String endTime) throws ClassNotFoundException {
        JSONArray jsonArray = new JSONArray();
        QueryBuilder queryBuilder = null;
        String fullyQualifiedName = packageName + entityName;
        if(localId == null) {
            queryBuilder = QueryBuilder.byClass(Class.forName(fullyQualifiedName));
        } else {
//            queryBuilder = QueryBuilder.byClass(DemoTest.class);
            queryBuilder = QueryBuilder.byInstanceId(localId, Class.forName(fullyQualifiedName));
//            queryBuilder = QueryBuilder.byInstanceId(localId, fullyQualifiedName);
        }

        //时间格式："2023-04-03T15:55:54"
        queryBuilder.from(LocalDateTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        queryBuilder.to(LocalDateTime.parse(endTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        List<CdoSnapshot> cdoSnapshotList = javers.findSnapshots(queryBuilder.build());
        jsonArray = JSONArray.parseArray(javers.getJsonConverter().toJson(cdoSnapshotList));
        return jsonArray;
    }

    /**
     *
     * @param localId 配置id
     * @param entityName Class名
     * @param startTime 查询的起始时间
     * @param endTime 查询的结束时间
     * @return
     */

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public Object findChanges(@RequestParam(required = false) Integer localId, String entityName, String startTime, String endTime) throws ClassNotFoundException {
        JSONArray jsonArray = new JSONArray();
        QueryBuilder queryBuilder = null;
        String fullyQualifiedName = packageName + entityName;
        if(localId == null) {
            queryBuilder = QueryBuilder.byClass(Class.forName(fullyQualifiedName));
        } else {
//            queryBuilder = QueryBuilder.byClass(DemoTest.class);
            queryBuilder = QueryBuilder.byInstanceId(localId, Class.forName(fullyQualifiedName));
//            queryBuilder = QueryBuilder.byInstanceId(localId, fullyQualifiedName);
        }
        //时间格式："2023-04-03T15:55:54"
        queryBuilder.from(LocalDateTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        queryBuilder.to(LocalDateTime.parse(endTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        List<Change> changes = javers.findChanges(queryBuilder.build());
        jsonArray = JSONArray.parseArray(javers.getJsonConverter().toJson(changes));
        return jsonArray;
    }

    /**
     *
     * @param localId 配置id
     * @param entity 实体类的包全限定名
     * @param commitId 修改的commitId
     * @return
     */
    @RequestMapping(value = "/commit/change", method = RequestMethod.GET)
    public Object findChangesByCommit(@RequestParam(required = false) Integer localId, String entity, BigDecimal commitId) throws ClassNotFoundException {
        JSONArray jsonArray = new JSONArray();
        QueryBuilder queryBuilder = null;
        if(localId == null) {
            queryBuilder = QueryBuilder.byClass(Class.forName(entity));
        } else {
            queryBuilder = QueryBuilder.byInstanceId(localId, Class.forName(entity));
//            queryBuilder = QueryBuilder.byInstanceId(localId, fullyQualifiedName);
        }
        queryBuilder.withCommitId(commitId);
        List<Change> changes = javers.findChanges(queryBuilder.build());
        jsonArray = JSONArray.parseArray(javers.getJsonConverter().toJson(changes));
        return jsonArray;
    }

    /**
     * 感觉像是简略版的快照，好像时间还长点
     * @param entityName Class名
     * @return
     */
    @RequestMapping(value = "/shadow", method = RequestMethod.GET)
    public Object findShadows(String entityName) throws ClassNotFoundException {
        String fullyQualifiedName = packageName + entityName;
        JqlQuery query = QueryBuilder.byClass(Class.forName(fullyQualifiedName)).build();
        List<Shadow<Object>> shadows = javers.findShadows(query);
        return JSONArray.parseArray(javers.getJsonConverter().toJson(shadows));
    }

    /**
     * 获取所有的可查询的变更审计列表
     * @return
     */
    @RequestMapping(value = "/findAnnotation", method = RequestMethod.GET)
    public Object findAnnotation() {
        return BaseClazzUtil.JaversExpandInfoAnnotationList();
    }

}
