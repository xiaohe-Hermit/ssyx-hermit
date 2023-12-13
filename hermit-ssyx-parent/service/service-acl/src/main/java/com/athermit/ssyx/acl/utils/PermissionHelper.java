package com.athermit.ssyx.acl.utils;

import com.athermit.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

    public static List<Permission> buildPermission(List<Permission> allList) {

        List<Permission> trees = new ArrayList<>();
        for (Permission permission : allList) {
            if (permission.getPid() == 0) {
                permission.setLevel(1);
                trees.add(findChildren(permission, allList));
            }
        }

        return trees;
    }

    /**
     * permission是上层节点，往下找
     *
     * @param permission
     * @param allList
     * @return
     */
    private static Permission findChildren(Permission permission, List<Permission> allList) {
        //先给它的儿子们留个座
        permission.setChildren(new ArrayList<>());

        for (Permission it : allList) {
            //判断是否为父子关系
            if (permission.getId().longValue() == it.getPid().longValue()) {
                //设置下一层的等级
                it.setLevel(permission.getLevel() + 1);

                //认领这个儿子
                permission.getChildren().add(findChildren(it, allList));
            }
        }

        return permission;
    }

}
