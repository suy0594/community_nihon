import React,{ useState, useEffect } from "react";
import axios from 'axios';

//Group name, Group Id, Group picture, Group description, number of Posts, number of member, 

const Group = () => {

    const [group, setGroup] = useState([]); 

  useEffect(() => {
    axios.get('http://localhost:8080/api/communities/${id}') 
      .then(response => {
        setGroup(response.data); 
      })
      .catch(error => {
        console.error('Error:', error); 
      });
  }, []); 
    return (
        <>
        <div className="profile-container">
        <div className="profile-header">
        <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
            <div className="profile-info">
            <h2 className="profile-name">{Group.title}</h2>
            <p className="profile-handle">@{Group.id}</p>
            <p className="profile-bio">{Group.description}</p>
            <div className="profile-stats">
                <p>Posts: {Group.number_of_posts}</p>
                <p>Member: {Group.number_of_member}</p>
            </div>
            </div>
        </div>
        <div className="tweet-list">
            <div className="tweet">
            <Post />
            </div>
            <div className="tweet">
            <Post />
            </div>
        </div>
        </div>
        </>
    );
};

export default Group;