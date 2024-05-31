import React from "react";

const AddPictureContainer = () => {
    <form name="form" method="post" action="http://localhost:8080/userProfile" enctype="multipart/form-data">
    <input type="file" name="files" multiple="multiple"/>
    <input type="submit" id="submit" value="send picture"/>
</form>
};
export default AddPictureContainer;