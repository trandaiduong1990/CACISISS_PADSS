// UDMv4.301 //
/***************************************************************/
var um={'menuClasses':[],'itemClasses':[],'menuCode':[]};
/***************************************************************\

  ULTIMATE DROP DOWN MENU Version 4.301 by Brothercake
  http://www.udm4.com/
  
  This script may not be used or distributed without license

\***************************************************************/


/***************************************************************\
 * CORE CONFIGURATION
\***************************************************************/

//path to images folder 
um.baseSRC = "../images/";

//reset behaviors
um.reset = [
	'no',  // reset from document mouse click ["yes"|"no"]
	'no',  // reset from window resize ["yes"|"no"]
	'no',  // reset from text resize ["yes"|"no"]
	];

//navbar orientation
um.orientation = [
	"vertical",	// alignment ["vertical"|"horizontal"|"popup"]
	"left",		// h align ["left"|"right"]
	"top",		// v align ["top"|"bottom"]
	"absolute",	// positioning ["relative"|"absolute"|"fixed"|"allfixed"]
	"2px",		// x position ["em"|"ex"|"px"|"0"]
	"10px",		// y position ["em"|"ex"|"px"|"0"]
	"1000",		// z order ["0" to "10000"] (menu takes 20000 headroom)
	];
	

//navbar list output
um.list = [
	"rigid",	// horizontal overflow ["rigid"|"flexible"]
	"yes",		// show menus to IE-based screenreaders ["yes"|"no"]
	"no",		// hide static menus for netscape 4 ["yes"|"no"]
	];

//menu behaviors	
um.behaviors = [
	"200",		// open timer ["milliseconds"|"0"]
	"never",	// close timer ["milliseconds"|"never"|"0"]
	"yes",		// reposition menus to stay inside the viewport ["yes"|"no"]
	"default",	// manage windowed controls for win/ie ["default","hide","iframe","none"]
	];



//horizontal continuation strip
um.hstrip = [
	"none",		// background ["color"|"#hex"|"rgb()"|"image.gif"|"none"]
	"yes",		// copy navbar item margin-right to margin-bottom ["yes"|"no"]
	];
	
	

/***************************************************************\
 * NAVBAR DEFAULT STYLES
\***************************************************************/


//styles which apply to the navbar
um.navbar = [
	"-6",		// nav -> menu x-offset (+-)["n" pixels]
	"-11",		// nav -> menu y-offset (+-)["n" pixels]
	"143px",	// width ["em"|"ex"|"px"] (vertical navbar only - horizontal navbar items have "auto" width) ("%" doesn't work right) 
	];


//styles which apply to each navbar item
um.items = [
	"1",		// margin between items ["n" pixels]
	"0",		// border size ["n" pixels] (single value only)
	"collapse",	// border collapse ["collapse"|"separate"] (only applies when margin = "0")
	"#ead4a4",	// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#fae4b4",	// hover/focus border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// hover/focus border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#ead4a4",	// visited border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// visited border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"0",		// left/right padding ["n" pixels] (single value only)
	"3",		// top/bottom padding ["n" pixels] (single value only)
	"#f0f0f0",	// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#DADCF1",	// hover/focus background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#f0f0f0",	// visited background ["color"|"#hex"|"rgb()"|"image.gif"]
	"11px",		// font size ["em"|"ex"|"%"|"px"|"pt"|"absolute-size"|"relative-size"]
	"arial",	// font family ["font1,font2,font3"] (always end with a generic family name)
	"normal",	// font weight ["normal"|"bold"|"bolder"|"lighter|"100" to "900"]
	"none",		// text decoration ["none"|"underline"|"overline"|"line-through"]
	"left",		// text-align ["left"|"right"|"center"]
	"#000000",	// color ["color"|"#hex"|"rgb()"]
	"#000000",	// hover/focus color ["color"|"#hex"|"rgb()"]
	"#000000",	// visited color ["color"|"#hex"|"rgb()"]
	"normal",	// font-style ["normal"|"italic"|"oblique"]
	"normal",	// hover/focus font-style ["normal"|"italic"|"oblique"]
	"normal",	// visited font-style ["normal"|"italic"|"oblique"]
	"",			// additional link CSS (careful!) letter-spacing:1px !important;
	"",			// additional hover/focus CSS (careful!)
	"",			// additional visited CSS (careful!)
	"xpexpand3_s.gif",// menu indicator character/image ["text"|"image.gif"|"none"] 
	"xpexpand2_s.gif",// menu indicator rollover character/image ["text"|"image.gif"|"none"] (must be same type)
	"25",		// clipping width of indicator image ["n" pixels] (only when using image arrows)
	"",		// alt text of indicator image ["text"] (only when using image arrows)
	];


/***************************************************************\
 * MENU DEFAULT STYLES
\***************************************************************/


//styles which apply to each menu
um.menus = [
	"-7",		// menu -> menu x-offset (+-)["n" pixels]
	"-12",		// menu -> menu y-offset (+-)["n" pixels]
	"0",		// border size ["n" pixels] (single value only) 
	"#005894",// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"1px",	// width ["em"|"ex"|"px"]
	"2",		// padding ["n" pixels] (single value only) 
	"#DBE7F2",	// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"",		// additional menu CSS (careful!) (you can use a transition here but *not* a static filter)
	"none",// shadow background ["color"|"#hex"|"rgb()"|"image.gif"|"none"]
	"2px",		// shadow offset (+-) ["em"|"px"|"pt"|"%"|"0"]
	"filter:progid:DXImageTransform.Microsoft.Shadow(color=#707070,direction=135,strength=2);",// additional shadow layer CSS (if you use a Microsoft.Shadow filter here then Win/IE5.5+ will do that *instead* of default shadow)
	];


//styles which apply to each menu item
um.menuItems = [
	"0",		// margin around items ["n" pixels] (single value only; margins are like table cellspacing)
	"0",		// border size ["n" pixels] (single value only)
	"collapse",	// border collapse ["collapse"|"separate"] (only applies when margin = "0")
	"#005894",	// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#005894",	// hover/focus border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// hover/focus border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#005894",	// visited border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// visited border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"0",		// left/right padding ["n" pixels] (single value only) 
	"2",		// top/bottom padding ["n" pixels] (single value only) 
	"#DADCF1",	// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#f0f0f0",	// hover/focus background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#DADCF1",	// visited background ["color"|"#hex"|"rgb()"|"image.gif"]
	"11px",		// font size ["em"|"ex"|"%"|"px"|"pt"|"absolute-size"|"relative-size"]
	"arial",	// font family ["font1,font2,font3"] (always end with a generic family name)
	"normal",	// font weight ["normal"|"bold"|"bolder"|"lighter|"100" to "900"]
	"none",		// text decoration ["none"|"underline"|"overline"|"line-through"]
	"left",		// text-align ["left"|"right"|"center"]
	"#000000",	// color ["color"|"#hex"|"rgb()"]
	"#000000",	// hover/focus color ["color"|"#hex"|"rgb()"]
	"#000000",	// visited color ["color"|"#hex"|"rgb()"]
	"normal",	// font-style ["normal"|"italic"|"oblique"]
	"normal",	// hover/focus font-style ["normal"|"italic"|"oblique"]
	"normal",	// visited font-style ["normal"|"italic"|"oblique"]
	"",		// additional link CSS (careful!)
	"",		// additional hover/focus CSS (careful!)
	"",		// additional visited CSS (careful!)
	"none",// submenu indicator character/image ["text"|"image.gif"|"none"] 
	"none",// submenu indicator rollover character/image ["text"|"image.gif"|"none"] (must be the same type)
	"3",		// clipping width of indicator image ["n" pixels] (only when using image arrows)
	"",		// alt text of indicator image ["text"] (only when using image arrows)
	];


/***************************************************************\
 * MENU CLASSES [comment out or remove if not using]
\***************************************************************/

/*
//classes which apply to menus [optional]
um.menuClasses["orangeMenu"] = [
	"#fdcb95 #a97742 #a97742 #fdcb95",// border colors ["color"|"#hex"|"rgb()"]
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"8em",		// width ["em"|"ex"|"px"]
	"#fec",		// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"",		// additional menu CSS (careful!) (you can use a transition here but *not* a static filter)
	"orangeshadow.png",// shadow background ["color"|"#hex"|"rgb()"|"image.gif"|"none"] 
	"2px",		// shadow offset (+-) ["em"|"px"|"pt"|"%"|"0"]
	"filter:progid:DXImageTransform.Microsoft.Shadow(color=#ddbbaa,direction=135,strength=2);", // additional shadow layer CSS (if you use a Microsoft.Shadow filter here then Win/IE5.5+ will do that *instead* of default shadow)
	];


//classes which apply to menu items [optional]
um.itemClasses["orangeMenuItem"] = [
	"#fec",		// border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#edbb85",	// hover/focus border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// hover/focus border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#fec",		// visited border colors ["color"|"#hex"|"rgb()"] (single, double or four values)
	"solid",	// visited border styles ["solid"|"double"|"dotted"|"dashed"|"groove"|"ridge"|"inset"|"outset"] (single, double or four values; be careful with using "none")
	"#fec",		// background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#f8fbd0",	// hover/focus background ["color"|"#hex"|"rgb()"|"image.gif"]
	"#fec",		// visited background ["color"|"#hex"|"rgb()"|"image.gif"]
	"70%",		// font size ["em"|"ex"|"%"|"px"|"pt"|"absolute-size"|"relative-size"]
	"tahoma,sans-serif",// font family ["font1,font2,font3"] (always end with a generic family name)
	"normal",	// font weight ["normal"|"bold"|"bolder"|"lighter|"100" to "900"]
	"none",		// text decoration ["none"|"underline"|"overline"|"line-through"]
	"left",		// text-align ["left"|"right"|"center"]
	"#803090",	// color ["color"|"#hex"|"rgb()"]
	"#5656bd",	// hover/focus color ["color"|"#hex"|"rgb()"]
	"#803090",	// visited color ["color"|"#hex"|"rgb()"]
	"normal",	// font-style ["normal"|"italic"|"oblique"]
	"normal",	// hover/focus font-style ["normal"|"italic"|"oblique"]
	"normal",	// visited font-style ["normal"|"italic"|"oblique"]
	"",		// additional link CSS (careful!)
	"",		// additional hover/focus CSS (careful!)
	"",		// additional visited CSS (careful!)
	"right-purple.gif",// submenu indicator character/image ["text"|"image.gif"|"none"] (must be the same type as default submenu indicator)
	"right-blue.gif",// submenu indicator rollover character/image ["text"|"image.gif"|"none"] (must be the same type)
	"..",		// alt text of indicator image  ["text"] (only when using image arrow)
	];
*/


