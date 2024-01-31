import React from 'react';
import FlightsManager from './FlightsManager';
import CrewManager from './CrewManager';
import AirplaneManager from './AirplaneManager';

const AdminPage = () => {
    return (
        <div className="bg-white rounded-5 p-3">
            <h1>Admin Page</h1>
            <FlightsManager />
            <CrewManager />
            <AirplaneManager />
        </div>
    );
};

export default AdminPage;