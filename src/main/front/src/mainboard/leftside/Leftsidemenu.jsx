import React from 'react';
import './Leftsidemenu.css';
import {Leftsidecontent} from "./Leftsidecontent";

const Leftsidememu = ({userId}) => {
    return (
        <>
            <div className='Sidebar'>
                <ul className='SidebarList'>
                    {Leftsidecontent.map((value, key) => {
                        return (
                            <li key={key} id={window.location.pathname == value.link ? "active" : ""} 
                            className='row' onClick={() => {window.location.pathname = value.link;}}>
                                <div id="icon">{value.icon}</div>
                                <div id="icon">{value.title}</div>
                            </li>
                        );
                    })}
                </ul>
            </div>
        </>
    )
};

export default Leftsidememu;