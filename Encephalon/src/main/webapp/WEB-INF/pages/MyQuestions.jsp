<div class="container " id="myQuestionsDiv" ng-controller="myQuestionsController">



    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <span class="nav-link active" data-toggle="tab" href="#home" role="tab">Word Meaning</span>
        </li>
        <li class="nav-item">
            <span class="nav-link" data-toggle="tab" href="#profile"  role="tab">Point To Remember</span>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div class="tab-pane active" id="home" role="tabpanel">

            <table class="table mt-4 table-hover table-sm">
                <caption>Word Meanings added by you</caption>
                <thead>
                <th scope="col" style="width: 20%">#</th>
                <th scope="col" style="width: 50%">Word</th>
                <th scope="col" style="width: 30%">Options</th>

                </thead>
                <tbody>
                    <tr ng-repeat="wordMeaning in wordMeanings">
                        <th scope="row">{{$index + 1}}</th>
                        <td>{{wordMeaning.word}}</td>
                        <td>
                            <button data-id="{{}"  data-toggle='modal' data-target='#trashConfirm' class="btn btn-default" >
                                <i class="fa fa-trash" aria-hidden="true"></i>    
                            </button>
                            <button  data-toggle='modal' data-windex="{{$index}}"  ng-click="viewWord($event)" data-target='#ViewWordMeaning' class="btn btn-default">
<!--                                <i class="fa fa-eye" aria-hidden="true"></i>    -->
View
                            </button>
                        </td>
                    </tr>
                </tbody>

            </table>
        </div>
        <div class="tab-pane" id="profile" role="tabpanel">

            <table class="table mt-4 table-hover table-sm">
                <caption>Point To Remember added by you</caption>
                <thead>
                <th scope="col" style="width: 20%">#</th>
                <th scope="col" style="width: 50%">Point</th>
                <th scope="col" style="width: 30%">Options</th>

                </thead>
                <tbody>

                    <tr ng-repeat="pointToRemember in pointToRemembers">
                        <th>{{$index + 1}}</th>
                        <td>{{pointToRemember.point}}</td>
                        <td>
                            <button data-toggle='modal' data-target='#trashConfirm' class="btn btn-default">

                                <i class="fa fa-trash" aria-hidden="true"></i>    
                            </button>

                            <button class="btn btn-default">
                                <i class="fa fa-eye" aria-hidden="true"></i>    
                            </button>
                            <button class="btn btn-default">
                                <i class="fa fa-edit" aria-hidden="true"></i>    
                            </button>

                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- The Modal -->
    <div class="modal fade" id="trashConfirm" >
        <div class="modal-dialog modal-lg">
            <div class="modal-content modal-lg">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Confirm Trash Question!!</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body" >
                    <div class="d-block">
                        Are you sure?
                    </div>
                    

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">

                    <button type="button" class="btn btn-default "  data-dismiss="modal" >Cancel</button>
                    <button type="button" class="btn btn-danger"   ng-click="trashQuestion($event)" data-dismiss="modal">Trash</button>
                </div>

            </div>
        </div>
    </div>

    <!-- The Modal -->
    <div class="modal fade" id="ViewWordMeaning" >
        <div class="modal-dialog modal-lg">
            <div class="modal-content modal-lg">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Word Meaning</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body" >
                    
                    <div id="wordMeaningLayout1" class="d-block">
                        <!--for word--> 
                        <b id="wordSpan" >Word :</b>
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
                        <br/>
                        <br/>
                        <br/>
                        <div id="layout1RadioOptionsDiv" ng-click="questionAnswered($event)">
                            <input name="layout1RadioOptions" type="radio" > I Remember</input>
                            <br/>
                            <input name="layout1RadioOptions" type="radio" > I Don't Remember</input>
                        </div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">

                    <button type="button" class="btn btn-default "  data-dismiss="modal" >Close</button>
                </div>

            </div>
        </div>
    </div>
</div>  
