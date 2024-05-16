import React from "react";
import HomeIcon from '@mui/icons-material/Home';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import SearchIcon from '@mui/icons-material/Search';
import SettingsIcon from '@mui/icons-material/Settings';

export const Leftsidecontent = [
    {
        title : "HOME",
        icon : <HomeIcon />,
        link : "/home",
    },
    {
        title : "MY ACCOUNT",
        icon : <AccountCircleIcon />,
        link : "/my",
    },
    {
        title : "SEARCH",
        icon : <SearchIcon />,
        link : "/search",
    },
    {
        title : "SETTING",
        icon : <SettingsIcon />,
        link : "/setting",
    }
];