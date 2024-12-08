import React, { useState, useEffect } from "react";
import { Circle, MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import markerIcon from "leaflet/dist/images/marker-icon.png";
import markerShadow from "leaflet/dist/images/marker-shadow.png";
// import AttendanceForm from "./student-attendance-form/AttendanceForm";

L.Icon.Default.mergeOptions({
  iconUrl: markerIcon,
  shadowUrl: markerShadow,
});

const size: React.CSSProperties = {
  width: "100vw",
  height: "100vh",
};

const centerPosition: [number, number] = [-1.985827, 30.126899]; // Center of the circle
const radius = 1700; // Radius in meters

const Maps: React.FC = () => {
  const [studentPosition, setStudentPosition] = useState<[number, number] | null>(null);
  const [isWithinRadius, setIsWithinRadius] = useState(false);

  // Update `isWithinRadius` whenever the student's position changes
  useEffect(() => {
    if (studentPosition) {
      const distance = L.latLng(centerPosition).distanceTo(L.latLng(studentPosition));
      setIsWithinRadius(distance <= radius);
    }
  }, [studentPosition]);

  // Fetch student's current position
  const getStudentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          setStudentPosition([latitude, longitude]);
        },
        (error) => {
          console.error("Error getting location", error);
        }
      );
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  };

  useEffect(() => {
    getStudentLocation();
  }, []);

  return (
    <div>
      <MapContainer
        style={size}
        center={centerPosition}
        zoom={13}
        scrollWheelZoom={true}
      >
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Circle
          center={centerPosition}
          pathOptions={{ fillColor: "red", opacity: 0.1 }}
          radius={radius}
        />
        {studentPosition && (
          <Marker position={studentPosition}>
            <Popup>Your Current Position</Popup>
          </Marker>
        )}
      </MapContainer>

      {/* Pass isWithinRadius as a prop to AttendanceForm */}
      {/* <AttendanceForm isWithinRadius={isWithinRadius} /> */}
    </div>
  );
};

export default Maps;
