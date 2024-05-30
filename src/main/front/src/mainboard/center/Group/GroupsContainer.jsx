import React,{ useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import axios from 'axios';
import PreGroup from "./PreGroup";

//need Group info =>
//Group name, Group Id, Group picture, Group description, number of Posts, number of member,

const GroupsContainer = () => {
    const [groups, setGroups] = useState([]); 

  useEffect(() => {
    axios.get('http://localhost:8080/api/communities') 
      .then(response => {
        setGroups(response.data); 
      })
      .catch(error => {
        console.error('Error:', error); 
      });
  }, []); 

    return (
        <>
        <PreGroup />
        <PreGroup />
        <div className="tweet-list">
        <div className="tweet">
        {groups.length > 0 ? (
        groups.map(groups => (
          <PreGroup key={groups.id} id={groups.id} title={groups.title} desc_text={groups.description} num_member={groups.number_of_member} num_posts={groups.number_of_posts} />
        ))
      ) : (
        <p>Loading...</p> 
      )}
        </div>
      </div>
        </>
    );
};
export default GroupsContainer;