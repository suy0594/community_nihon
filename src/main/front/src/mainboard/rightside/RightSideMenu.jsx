// Leftsidemenu.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './RightSideMenu.css';
import RightSideContent from "./RightSideContent"; // 関数をインポート

const RightSideMenu = ({ userId }) => {
  const navigate = useNavigate();

  return (
    <>
      <div className='Sidebar'>
        <ul className='SidebarList'>
          {RightSideContent({ userId }).map((value, key) => {

            return (
              <li
                key={key}
                id={window.location.pathname == value.link ? "active" : ""}
                className='row'
                onClick={() => {
                  navigate(value.link);
                }}
              >
                <div id="icon">{value.icon}</div>
                <div id="icon">{value.title}</div>
              </li>
            );
          })}
        </ul>
      </div>
    </>
  );
};

export default RightSideMenu;