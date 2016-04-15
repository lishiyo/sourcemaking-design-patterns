<?php
/*
 *  Abstract Factory classes
 */

interface iFactory {
    public function createInstance();
}
 
abstract class DB_Abstraction_Factory {
    protected $settings = array();
    protected function __construct() {
        $this->settings = Settings::getInstance();
    }
 
    abstract public function createInstance();
}
 
class DB_Abstraction_Factory_ADODB extends DB_Abstraction_Factory implements iFactory {
    public function __construct() {
        parent::__construct();
    }
    public function createInstance() {
        require_once('/path/to/adodb_lite/adodb.inc.php');
        $dsn = $this->settings['db.dsn'];
        $db = ADONewConnection($dsn);
        return $db;
    }
}
 
class DB_Abstraction_Factory_MDB2 extends DB_Abstraction_Factory implements iFactory {
    public function __construct() {
        parent::__construct();
    }
    public function createInstance() {
        require_once 'MDB2.php';
        $dsn = $this->settings['db.dsn'];
        $db = MDB2::factory($dsn);
        return $db;
    }
}

interface iAbstractFactory {
    public static function getFactory();
}
 
class DB_Abstraction_AbstractFactory implements iAbstractFactory {
    private function __construct() 	
    }
    public static function getFactory() {
        $settings = Settings::getInstance();
        require_once('iFactory.php');
        require_once('DB_Abstraction_Factory.php');
        switch($settings['db.library'])
        {
            case 'adodblite':
                require_once('DB_Abstraction_Factory_ADODBLITE.php');
                $factory = new DB_Abstraction_Factory_ADODBLITE();
            break;
            case 'mdb2';
                require_once('DB_Abstraction_Factory_MDB2.php');
                $factory = new DB_Abstraction_Factory_MDB2();
            break;
        }
        return $factory;
    }
 }

/*
 *  Client's code
 */

// instantiate Abstract Factory
$abstractfactory = new DB_Abstraction_AbstractFactory();
 
// fetch a concrete Factory (decision handled in Abstract Factory static method)
$factory = $abstractfactory::getFactory();
 
// use concrete Factory to create a database connection object from
// the selected database abstraction library
$db = $factory->createInstance();
?>
