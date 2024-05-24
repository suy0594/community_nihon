import React from "react";
import HomeIcon from '@mui/icons-material/Home';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import SearchIcon from '@mui/icons-material/Search';
import SettingsIcon from '@mui/icons-material/Settings';

export const Leftsidecontent = [
    {
        title : "HOME",
        icon : <HomeIcon />,
        link : "/main/home",
    },
    {
        title : "MY ACCOUNT",
        icon : <AccountCircleIcon />,
        link : "/main/my",
    },
    {
        title : "SEARCH",
        icon : <SearchIcon />,
        link : "/main/search",
    },
    {
        title : "SETTING",
        icon : <SettingsIcon />,
        link : "/main/setting",
    }
];