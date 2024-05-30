import React,{ useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import axios from 'axios';
import PreGroup from "./PreGroup";
import Group from "./Group";

//need Group info =>
//Group name, Group Id, Group picture, Group description, number of Posts, number of member,

const GroupsContainer = () => {
    const [groups, setGroups] = useState([]); 

  useEffect(() => {
    axios.get(`http://localhost:8080/api/communities`)
      .then(response => {
        setGroups(response.data); 
      })
      .catch(error => {
        console.error('Error:', error); 
      });
  }, []);

    return (
        <>
            <div className="tweet-list">
                <div className="tweet">
                    {groups.length > 0 ? (
                        groups.filter(group => group.is_group).map(group => (
                            <PreGroup key={group.id} id={group.id} title={group.title} desc_text={group.description} num_member={group.number_of_member} num_posts={group.number_of_posts} />
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