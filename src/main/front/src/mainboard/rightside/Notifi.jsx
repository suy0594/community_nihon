import React from 'react';
import "./notifi.css"

const Notifi = () => {
    return (
        <>
        <div>
            <div className='account_info'>
                <div className='picture'>
                    <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                </div>
                <p className='userName'>User</p>
            </div>
            <div>
                <p className='notifiText'>User posted</p>
            </div>
        </div>
        </>
    );
};

export default Notifi;