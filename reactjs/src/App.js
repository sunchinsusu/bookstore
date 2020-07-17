import Menu from './component/Menu';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import React from 'react';
import routes from './routes';
import AppBar from './component/AppBar';
import Navigation from './component/Navigation';
const App = () => {
  const showContentMenus = (routes) => {
    var result = null;
    if (routes.length > 0) {
      result = routes.map((route, index) => {
        return (
          <Route
            exact
            key={index}
            path={route.path}
            component={route.main}
          />
        );
      });

    }
    return <Switch>{result}</Switch>;
  }



  return (
    <Router>
      <div>
        
        {showContentMenus(routes)}
      </div>
    </Router>
  );
}

export default App; 