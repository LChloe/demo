-- auto-generated definition
create table jv_commit
(
    commit_pk           bigint auto_increment
        primary key,
    author              varchar(200)   null,
    commit_date         timestamp(3)   null,
    commit_date_instant varchar(30)    null,
    commit_id           decimal(22, 2) null
);

create index jv_commit_commit_id_idx
    on jv_commit (commit_id);



-- auto-generated definition
create table jv_commit_property
(
    property_name  varchar(191) not null,
    property_value varchar(600) null,
    commit_fk      bigint       not null,
    primary key (commit_fk, property_name),
    constraint jv_commit_property_commit_fk
        foreign key (commit_fk) references jv_commit (commit_pk)
);

create index jv_commit_property_commit_fk_idx
    on jv_commit_property (commit_fk);

create index jv_commit_property_property_name_property_value_idx
    on jv_commit_property (property_name, property_value(191));



-- auto-generated definition
create table jv_global_id
(
    global_id_pk bigint auto_increment
        primary key,
    local_id     varchar(191) null,
    fragment     varchar(200) null,
    type_name    varchar(200) null,
    owner_id_fk  bigint       null,
    constraint jv_global_id_owner_id_fk
        foreign key (owner_id_fk) references jv_global_id (global_id_pk)
);

create index jv_global_id_local_id_idx
    on jv_global_id (local_id);

create index jv_global_id_owner_id_fk_idx
    on jv_global_id (owner_id_fk);



-- auto-generated definition
create table jv_snapshot
(
    snapshot_pk        bigint auto_increment
        primary key,
    type               varchar(200) null,
    version            bigint       null,
    state              longtext     null,
    changed_properties text         null,
    managed_type       varchar(200) null,
    global_id_fk       bigint       null,
    commit_fk          bigint       null,
    constraint jv_snapshot_commit_fk
        foreign key (commit_fk) references jv_commit (commit_pk),
    constraint jv_snapshot_global_id_fk
        foreign key (global_id_fk) references jv_global_id (global_id_pk)
);

create index jv_snapshot_commit_fk_idx
    on jv_snapshot (commit_fk);

create index jv_snapshot_global_id_fk_idx
    on jv_snapshot (global_id_fk);



-- auto-generated definition
create table operational_audit
(
    id                bigint auto_increment comment '主键'
        primary key,
    prefix            varchar(125) null comment '标识前缀',
    service_name      varchar(125) null comment '服务名',
    title             varchar(512) null comment '操作标题',
    oa_id             varchar(125) null comment '操作人id',
    log_params_result longtext     null comment '需要打印的变量',
    log_params        longtext     null comment '需要打印的变量名',
    request_url       varchar(512) null comment '请求url',
    request_method    varchar(45)  null comment '请求类型',
    servlet_path      varchar(255) null comment '路由',
    full_params       longtext     null comment '所有的请求参数',
    exec_status       int          null comment '请求执行状态',
    exec_msg          longtext     null comment '请求错误信息',
    create_date       datetime     null comment '创建日期',
    update_date       datetime     null comment '修改日期',
    version           int          null comment '版本号'
)
    comment '操作审计表';

