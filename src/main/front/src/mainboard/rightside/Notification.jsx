import React from 'react';
import Notifi from './Notifi'

const Notification = () => {
    return (
        <>
        <div className='noti_head'>
            <p>Notifications</p>
        </div>
        <div className='notifi'>
            <Notifi />
        </div>  
        </>
    );
};

export default Notification;