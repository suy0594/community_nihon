import React from 'react';
import Notifi from './Notifi'
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';

const Notification = () => {
    return (
        <>
            <div className='NotifiContainer'>
                <div className='noti_head'>
                    <p>Notifications</p>
                </div>
                <div className='notifi'>
                    <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                        <Notifi />
                        <Notifi />
                        <Notifi />
                        <Notifi />
                    </List>
                </div>
            </div>
            <div className='notifi'>
<<<<<<< HEAD
                <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                    <Notifi />
                    <Notifi />
                    <Notifi />
                    <Notifi />
                    <Notifi />
                    <Notifi />
                    <Notifi />
                    <Notifi />
                </List>
            </div>
=======
            <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                <Notifi />
                <Notifi />
                <Notifi />
                <Notifi />
            </List>
            </div>  
        </div>
>>>>>>> a444d1e7cec8938358c33ba238f7d8cc45ee970c
        </>
    );
};

export default Notification;