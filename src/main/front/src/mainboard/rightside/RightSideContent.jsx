import React from "react";
import PostAddIcon from '@mui/icons-material/PostAdd';
import GroupAddIcon from '@mui/icons-material/GroupAdd';

export const RightSideContent = [
    {
        title : "CREATE POST",
        icon : <PostAddIcon />,
        link : "/main/createPost",
    },
    {
        title : "CREATE GROUP",
        icon : <GroupAddIcon />,
        link : "/main/createGroup",
    },
];