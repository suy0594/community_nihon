import React from "react";
import PostAddIcon from '@mui/icons-material/PostAdd';
import GroupAddIcon from '@mui/icons-material/GroupAdd';

const RightSideContent = ({userId}) => [
    {
        title : "CREATE PRIVATE POST",
        icon : <PostAddIcon />,
        link : `/${userId}/createPost`,
    },
    {
        title : "CREATE GROUP",
        icon : <GroupAddIcon />,
        link : `/${userId}/createGroup`,
    },
];

export default RightSideContent;