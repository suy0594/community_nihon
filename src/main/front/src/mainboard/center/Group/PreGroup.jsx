import React from "react";
import GroupButton from "./GroupButton";

const PreGroup = () => {
    return(
        <>
        <div className="profile-container">
            <div className="profile-header">
                <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                <div className="profile-info">
                    <h2 className="profile-name">Group1</h2>
                    <p className="profile-handle">@GroupID</p>
                    <p className="profile-bio">This is Group.</p>
                    <div className="profile-stats">
                        <p>Posts: 100</p>
                        <p>Members: 50</p>
                    </div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
                </div>
            </div>
        </div>
        <GroupButton />
        </>
    );
};

export default PreGroup;