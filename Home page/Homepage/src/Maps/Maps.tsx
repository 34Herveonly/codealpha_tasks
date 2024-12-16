import React, { useEffect, useState } from "react";
import L, { Marker as LeafletMarker } from "leaflet";
import "leaflet/dist/leaflet.css";

const Maps: React.FC = () => {
  const [userPosition, setUserPosition] = useState<[number, number] | null>(null);
  const [isWithinRadius, setIsWithinRadius] = useState(false);

  useEffect(() => {
    // Center of the circle (Kigali, Rwanda)
    const centerPosition: [number, number] = [-1.9441, 30.0619];
    const radius = 3; // Circle radius in meters

    // Initialize the map
    const map = L.map("map").setView(centerPosition, 19); // Set initial zoom level to 19

    // Add OpenStreetMap tiles
    L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
      maxZoom: 25,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(map);

    // Add a circle to the map

    let marker: LeafletMarker | null = null;

    // Function to calculate distance between two points
    const calculateDistance = (lat1: number, lng1: number, lat2: number, lng2: number): number => {
      const toRad = (value: number) => (value * Math.PI) / 180;
      const R = 6371000; // Earth's radius in meters
      const dLat = toRad(lat2 - lat1);
      const dLng = toRad(lng2 - lng1);
      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      return R * c; // Distance in meters
    };

    // Success callback for geolocation
    const success = (pos: GeolocationPosition) => {
      const { latitude: lat, longitude: lng } = pos.coords;

      // Remove previous marker
      if (marker) {
        map.removeLayer(marker);
      }

      // Add marker at the user's position
      marker = L.marker([lat, lng]).addTo(map);

      // Update user's position state
      setUserPosition([lat, lng]);

      // Calculate the distance from the center of the circle
      const distance = calculateDistance(centerPosition[0], centerPosition[1], lat, lng);

      // Check if within radius
      if (distance <= radius) {
        setIsWithinRadius(true);
      } else {
        setIsWithinRadius(false);
        alert("You're not in class!");
      }

      // Ensure the map always shows the user's location
      map.setView([lat, lng], 19);
    };

    // Error callback for geolocation
    const error = (err: GeolocationPositionError) => {
      if (err.code === 1) {
        alert("Please allow your location to access the attendance form.");
      } else {
        alert("Cannot get current location. Please try again.");
      }
    };

    // Request user's location
    if (navigator.geolocation) {
      navigator.geolocation.watchPosition(success, error);
    } else {
      alert("Geolocation is not supported by your browser.");
    }

    // Cleanup on component unmount
    return () => {
      map.remove();
    };
  }, []);

  return (
    <div>
      <div id="map" style={{ width: "100%", height: "100vh" }} />
      {userPosition && isWithinRadius ? (
        <p style={{ textAlign: "center", color: "green" }}>You are within the classroom radius.</p>
      ) : (
        <p style={{ textAlign: "center", color: "red" }}>You are outside the classroom radius.</p>
      )}
    </div>
  );
};

export default Maps;
