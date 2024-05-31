import { useNavigate } from 'react-router-dom';
import Group from "./Group";
import React,{ useState, useEffect } from "react";

const PreGroup = ({id, title, desc_text, num_member, num_posts}) => {
    const navigate = useNavigate();
    const [imgError, setImgError] = useState(false);

    const handleImgError = () => {
        setImgError(true);
    };
    return(
        <>
        <div className="profile-container">
            <div className="profile-header">
            <img
            src={imgError ? "/images/errorImage.jpg" : "/images/testaccountinfo/knu_emeblem.jpg"}
            className='pict'
            alt='account picture'
            onError={handleImgError}
          />
                <div className="profile-info">
                    <h2 className="profile-name">{title}</h2>
                    <p className="profile-handle">@{id}</p>
                    <p className="profile-bio">{desc_text}</p>
                    <div className="profile-stats">
                        <p>Posts: {num_posts}</p>
                        <p>Members: {num_member}</p>
                    </div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
                </div>
            </div>
        </div>
        <button onClick={() => {navigate(`${id}`);}}>Go to the Group</button>
        </>
    );
};

export default PreGroup;