// Leftsidecontent.jsx
import React from "react";
import HomeIcon from '@mui/icons-material/Home';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import SearchIcon from '@mui/icons-material/Search';
import SettingsIcon from '@mui/icons-material/Settings';
import PeopleIcon from '@mui/icons-material/People';

const Leftsidecontent = ({ userId }) => [
  {
    title: "HOME",
    icon: <HomeIcon />,
    link: `/${userId}/home`,
  },
  {
    title: "MY ACCOUNT",
    icon: <AccountCircleIcon />,
    link: `/${userId}/my`,
  },
  {
    title: "GROUPS",
    icon: <PeopleIcon />,
    link: `/${userId}/groups`,
  },
  {
    title: "SEARCH",
    icon: <SearchIcon />,
    link: `/${userId}/search`,
  },
  {
    title: "SETTING",
    icon: <SettingsIcon />,
    link: `/${userId}/setting`,
  }
];

export default Leftsidecontent;