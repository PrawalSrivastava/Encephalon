<%-- 
    Document   : hello
    Created on : 6 Oct, 2018, 8:05:15 PM
    Author     : prawal
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Encephalon</title>


        <link rel="stylesheet" href='<c:url value="/resources/css/myCss.css" />' />
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.4/angular.min.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.4/angular-route.js"></script>

        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

        <!--         Optional theme 
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.js"></script>
    </head>
    <body ng-app="app" ng-controller="eApp" style="height: calc(100% - 56px)" class="">
        <!--Create Header Bar-->


        <!-- Responsive Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">Encephalon</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#/!">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            Actions
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#" data-toggle='modal' data-target='#addQuestionWizard'>Add Question</a>
                            <a class="dropdown-item" href="#" data-toggle='modal' data-target='#addPointWizard'>Add Point</a>
                            <a class="dropdown-item" href="#" data-toggle='modal' data-target='#addWordMeaningWizard'>Add Word Meaning</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#!MyQuestions">My Questions</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link" data-toggle='modal' data-target='#revisionInputModal'>Start Revision</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">Contact Us</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">About Us</a>
                    </li>
                </ul>

                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button title="Search" class="btn btn-danger my-2 my-sm-0" type="submit" >
                        <i class="fa fa-search" aria-hidden="true"></i>    

                    </button>
                </form>
                <button onclick="parent.location = '<c:url value="/j_spring_security_logout" />'" class="btn btn-default ml-2 my-2 my-sm-0">
                    <i class="fa fa-sign-out-alt" aria-hidden="true"></i>    
                    Logout
                </button>

            </div>
        </nav>
        <div class="container-fluid  pr-0 pl-0 h-100 " style="margin-top:3.5em;display: flex;width: 100%;align-items: stretch;">

            

                <div class="col-lg-2 bg-dark text-white">
                    <aside id="sidebar">
                        <div class="sidebar-header">
<!--                            <h3>Menu</h3>-->
                        </div>

                        <ul class="list-unstyled components">

                            <li >
                                <a href="#homeSubmenu" data-toggle="collapse" role="button" aria-expanded="true" class="dropdown-toggle">Topics</a>
                                <ul class="collapse list-unstyled" id="homeSubmenu">
                                    <li>
                                        <a href="#">Browse Topics</a>
                                    </li>
                                    <li>
                                        <a href="#">My Topics</a>
                                    </li>
                                </ul>
                            </li>
                            <li >
                                <a href="#" class="nav-item">About</a>
                            </li>

                        </ul>
                    </aside>

                </div>
                <main id="mainView" ng-view class="col-lg-10 pt-3" style="overflow-y: auto">
                </main>    

                <!-- The Modal -->
                <div class="modal fade" id="revisionInputModal" ng-controller="revisionInputModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Revision</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body" data-tabIdMax='1' data-tabIdMin='1'>
                                <div data-tabId='1' class="d-block form-group row" data-nextText='Start' data-previousText='Previous' data-action='submit'>
                                    <div class="row">
                                        <div class="col-4 col-lg-4">

                                            <div class="list-group list-group-flush">
                                                <span data-href="{{quesType.href}}" ng-repeat="quesType in questionTypes" ng-click="quesTypeClick($event)" class="list-group-item list-group-item-action">
                                                    <h5 class="list-group-item-heading">{{quesType.value}}</h5>
                                                    <p class="list-group-item-text">List Group Item Text</p>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="col-8 col-lg-8">
                                            <div class="row">
                                                <div id="RANDOM" ng-show="currentType.href == 'RANDOM'" class=" col-md-11">

                                                    <label for="point" class=" col-form-label">Number Of Questions: </label>

                                                    <input type="text" name="point" class="form-control" ng-model="fields.RANDOM.noOfQuestions"/>
                                                </div>

                                                <div id="WORD_MEANING" ng-show="currentType.href == 'WORD_MEANING'" class="col-md-11">

                                                    <label for="point" class=" col-form-label">Number Of Questions: </label>

                                                    <input type="text" name="point" class="form-control" ng-model="fields.WORD_MEANING.noOfQuestions"/>
                                                </div>

                                                <div id="POINT_TO_REMEMBER" ng-show="currentType.href == 'POINT_TO_REMEMBER'"  class="col-md-11">

                                                    <label for="point" class=" col-form-label">Number Of Questions: </label>

                                                    <input type="text" name="point" class="form-control" ng-model="fields.POINT_TO_REMEMBER.noOfQuestions"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default previousButton" data-tabId='0'>Previous</button>
                                <button ng-click="handleSubmit($event)" type="button" class="btn btn-primary nextButton" data-tabId="2">Next</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- The Modal -->
                <div class="modal fade" id="addQuestionWizard" ng-controller="addQuestionWizardController">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add Question</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body" data-tabIdMax='5' data-tabIdMin='1'>
                                <div data-tabId='1' class="d-block" data-nextText='Next' data-previousText='Previous' data-action='next'>tab 1</div>
                                <div data-tabId='2' class="d-none" data-nextText='Next' data-previousText='Previous' data-action='next'>tab 2</div>
                                <div data-tabId='3' class="d-none" data-nextText='Next' data-previousText='Previous' data-action='next'>tab 3</div>
                                <div data-tabId='4' class="d-none" data-nextText='Next' data-previousText='Previous' data-action='next'>tab 4</div>
                                <div data-tabId='5' class="d-none" data-nextText='Finish' data-previousText='Previous' data-action='submit'>tab 5</div>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default previousButton" data-tabId='0'>Previous</button>
                                <button type="button" class="btn btn-primary nextButton" data-tabId="2">Next</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- The Modal -->
                <div class="modal fade" id="addPointWizard" ng-controller="addPointWizardController">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add Point</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body" data-tabIdMax='3' data-tabIdMin='1'>
                                <form id="addPointWizardForm"  method="GET" action="#">
                                    <div data-tabId='1' class="d-block form-group row" data-nextText='Next' data-previousText='Previous' data-action='next'>

                                        <label for="point" class="col-2 col-form-label">Point: </label>
                                        <div class="col-10 ">
                                            <!--<input type="text" name="point" class="form-control" ng-model="fields.point"/>-->
                                            <textarea cols="160" rows="10" name="point" class="form-control" ng-model="fields.point"></textarea>
                                        </div>


                                    </div>
                                    <div data-tabId='2' class="d-none form-group row" data-nextText='Next' data-previousText='Previous' data-action='next'>

                                        <label for="answer" class="col-2 col-form-label">Answer: </label>
                                        <div class="col-10 ">
                                            <!--<input type="text" ng-model="fields.answer" name="answer" class="form-control"/>-->
                                            <textarea cols="160" rows="10" ng-model="fields.answer" name="answer" class="form-control"></textarea>
                                        </div>
                                    </div>
                                    <div data-tabId='3' class="d-none form-group row pl-3" data-nextText='Submit' data-previousText='Previous' data-action='submit'>

                                        <div class="form-check">
                                            <input type="checkbox" ng-model="fields.personalQuestion" class="form-check-input" name="metaDetails"  id="materialUnchecked">
                                            <label class="form-check-label" for="materialUnchecked">Personal Question</label>
                                        </div>

                                        <!--<textarea cols="160" rows="10" ng-model="fields.personalQuestions" name="metaDetails" class="form-control"></textarea>-->

                                    </div>

                                </form>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default previousButton" data-tabId='0'>Previous</button>
                                <button type="button" class="btn btn-primary nextButton" ng-click="handleSubmit($event)" data-tabId="2">Next</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- The Modal -->
                <div class="modal fade" id="addWordMeaningWizard" ng-controller="addWordMeaningController">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content modal-lg">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add Word Meaning</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body" data-tabIdMax='1' data-tabIdMin='1'>
                                <div data-tabId='1' class="d-block" data-nextText='Finish' data-previousText='Previous' data-action='submit'>
                                    <div class="row justify-content-center">
                                        <div class="col-lg-8">
                                            <form class="form-inline">
                                                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                                                <button class="btn btn-outline-success my-2 my-sm-0" ng-click="handleSearch($event)" type="submit">Search</button>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12" >
                                            <!--for word--> 
                                            <b id="wordSpan" ng-show="wmResult" >Word :</b>
                                            <span id="word" ng-bind="wmResult.word"></span>
                                            <!--for meaning-->
                                            <div id="meaningBlock" class="pl-3">
                                                <!--for noun-->
                                                <div ng-repeat="lexicalEntry in wmResult.lexicalEntries">
                                                    <b id="lexicalCategory" class="font-italic" >{{lexicalEntry.lexicalCategory}}</b>
                                                    <!--                                                    this is going to repeat
                                                                                                        for difinition-->
                                                    <div id="difinitionBlock" ng-repeat="entry in lexicalEntry.entries">
                                                        <div id="difinition" ng-repeat="sense in entry.senses">
                                                            <div ng-repeat="definition in sense.definitions" class="pl-3">
                                                                <!--for dfinition word-->
                                                                <span id="difinitionWord" class="text-muted" >Definition: </span>
                                                                <!--for definition--> 
                                                                <span id="definitionText" ng-bind="definition"></span>
                                                                <br/>
                                                            </div>
                                                            <!--for dfinition word-->
                                                            <span id="difinitionWord"  ng-show="definition.usage">Usage: </span>
                                                            <!--for definition--> 
                                                            <span id="definitionText" ng-bind="x.example"></span>
                                                            <!--repeat for synonyms-->
                                                            <span id="synonymsBlock" ng-repeat="y in x.synonyms">{{y}}</span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--for verb This is going to repeat or may be not-->
                                                <div>

                                                </div>
                                            </div>
                                        </div>  
                                        <!--                                        <div ng-repeat=" (key,val) in wmResult">
                                                                                    {{key}} : {{val}}
                                                                                    <br/> Changed<br/>
                                        
                                                                                </div>-->
                                    </div>
                                </div>

                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default previousButton" data-tabId='0'>Previous</button>
                                <button type="button" class="btn btn-primary nextButton" data-tabId="2" ng-click="handleSubmit($event)">Next</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                            </div>

                        </div>
                    </div>
                </div>

            
        </div>
    </body>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- jQuery Custom Scroller CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

    <!--Load the google chart api here-->
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src='<c:url value="/resources/js/myScript.js" />' type="text/javascript"></script>


</html>
